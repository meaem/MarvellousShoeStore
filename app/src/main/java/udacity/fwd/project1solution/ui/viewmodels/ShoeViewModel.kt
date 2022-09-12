package udacity.fwd.project1solution.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import udacity.fwd.project1solution.models.Shoe
import udacity.fwd.project1solution.ui.enums.ShoeDataError
import kotlin.math.roundToInt

const val MIN_SHOE_SIZE = 30
const val MAX_SHOE_SIZE = 60
const val MAX_LENGTH = 200

class ShoeViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<MutableList<Shoe>>(mutableListOf())

    val shoeList = Transformations.map(_shoeList) {
        it as List<Shoe>
    }

    private val _added = MutableLiveData<Boolean>()

    val added: LiveData<Boolean>
        get() = _added


    private var _shoe = Shoe()

    val shoe: Shoe
        get() = _shoe


    private val _addError = MutableLiveData<ShoeDataError?>()
    val addError: LiveData<ShoeDataError?>
        get() = _addError

    fun addShoe() {

        _added.value = if (validateData(shoe)) {
            _shoeList.value?.add(shoe)
        } else false
    }

    fun onAddComplete() {
        _added.value = false
        resetShoe()
    }

    private fun validateData(
        shoe: Shoe
    ): Boolean {
        if (shoe.name.isBlank()) {
            _addError.value = ShoeDataError.NAME_CAN_NOT_BE_BLANK
            return false
        } else {
            _addError.value = null
        }
        if (shoe.company.isBlank()) {
            _addError.value = ShoeDataError.COMPANY_CAN_NOT_BE_BLANK
            return false
        } else {
            _addError.value = null
        }
        if (shoe.size == null) {
            _addError.value = ShoeDataError.SIZE_CAN_NOT_BE_BLANK
            return false
        } else {
            if (shoe.size?.roundToInt() !in MIN_SHOE_SIZE..MAX_SHOE_SIZE) {
                _addError.value = ShoeDataError.SIZE_NOT_IN_ALLOWED_RANGE
                return false
            } else {
                _addError.value = null
            }
        }
        if (shoe.description.isNotBlank() && shoe.description.length > MAX_LENGTH) {
            _addError.value = ShoeDataError.DESC_LARGE_TEXT
            return false
        } else {
            _addError.value = null
        }


        return true
    }

    fun onAddCanceled() {
        _addError.value = null
        resetShoe()
    }

    fun resetShoe() {
        _shoe = Shoe()

    }
}