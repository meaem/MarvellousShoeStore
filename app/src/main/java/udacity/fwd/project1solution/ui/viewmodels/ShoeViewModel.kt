package udacity.fwd.project1solution.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import udacity.fwd.project1solution.models.Shoe
import udacity.fwd.project1solution.ui.enums.ShoeDataError
import kotlin.math.roundToInt

val MIN_SHOE_SIZE = 30
val MAX_SHOE_SIZE = 60
val MAX_LENGTH = 200

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


    private val _nameError = MutableLiveData<ShoeDataError?>()
    val nameError: LiveData<ShoeDataError?>
        get() = _nameError


    private val _companyError = MutableLiveData<ShoeDataError?>()
    val companyError: LiveData<ShoeDataError?>
        get() = _companyError

    private val _sizeError = MutableLiveData<ShoeDataError?>()
    val sizeError: LiveData<ShoeDataError?>
        get() = _sizeError

    private val _descError = MutableLiveData<ShoeDataError?>()
    val descError: LiveData<ShoeDataError?>
        get() = _descError


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
            _nameError.value = ShoeDataError.CAN_NOT_BE_BLANK
            return false
        } else {
            _nameError.value = null
        }
        if (shoe.company.isBlank()) {
            _companyError.value = ShoeDataError.CAN_NOT_BE_BLANK
            return false
        } else {
            _companyError.value = null
        }
        if (shoe.size == null) {
            _sizeError.value = ShoeDataError.CAN_NOT_BE_BLANK
            return false
        } else {
            if (shoe.size?.roundToInt() !in MIN_SHOE_SIZE..MAX_SHOE_SIZE) {
                _sizeError.value = ShoeDataError.NOT_IN_ALLOWED_RANGE
//                    String.format( "valid sizes between %d and %d only", MIN_SHOE_SIZE,MAX_SHOE_SIZE)
                return false
            } else {
                _sizeError.value = null
            }
        }
        if (shoe.description.isNotBlank() && shoe.description.length > MAX_LENGTH) {
            _descError.value = ShoeDataError.LARGE_TEXT
//                String.format("Description charaters should not exceed %d characters", MAX_LENGTH )
            return false
        } else {
            _descError.value = null
        }


        return true
    }

    fun onAddCanceled() {
        _nameError.value = null
        _companyError.value = null
        _sizeError.value = null
        _descError.value = null

        resetShoe()
    }

    fun resetShoe() {
        _shoe = Shoe()

    }
}