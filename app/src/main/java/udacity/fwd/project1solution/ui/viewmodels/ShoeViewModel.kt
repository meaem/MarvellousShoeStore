package udacity.fwd.project1solution.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import udacity.fwd.project1solution.models.Shoe
import kotlin.math.roundToInt

val MIN_SHOE_SIZE = 30
val MAX_SHOE_SIZE = 60
val MAX_LENGTH = 200

class ShoeViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<MutableList<Shoe>>(mutableListOf())

    val shoeList = Transformations.map(_shoeList) {
        it as List<Shoe>
    }
//    get() = _shoeList

    private val _added = MutableLiveData<Boolean>()

    val added: LiveData<Boolean>
        get() = _added


    private val _shoe = MutableLiveData<Shoe>()

    val shoe: LiveData<Shoe>
        get() = _shoe

    //    @Bindable
    var shoeName = ""

    //    @Bindable
    var comapnyName = ""

    //    @Bindable
    var shoeSize = ""

    //    @Bindable
    var shoeDesc = ""

//    private val _listIsEmpty = MutableLiveData<Boolean>()
//
//    val listIsEmpty: LiveData<Boolean>
//        get() = _listIsEmpty

    private val _nameError = MutableLiveData<String?>()
    val nameError: LiveData<String?>
        get() = _nameError


    private val _companyError = MutableLiveData<String?>()
    val companyError: LiveData<String?>
        get() = _companyError

    private val _sizeError = MutableLiveData<String?>()
    val sizeError: LiveData<String?>
        get() = _sizeError

    private val _descError = MutableLiveData<String?>()
    val descError: LiveData<String?>
        get() = _descError


    init {
        _shoe.value = Shoe()
    }

    //    fun addShoe(shoeName: String, comapnyName: String, shoeSize: String, shoeDesc: String) {
    fun addShoe() {

        _added.value = if (validateData(shoeName, comapnyName, shoeSize, shoeDesc)) {
//            _listIsEmpty.value=false
            _shoeList.value?.add(Shoe(shoeName, shoeSize.toDouble(), comapnyName, shoeDesc))
        } else false
    }

    fun onAddComplete() {
        _added.value = false
        resetShoe()
    }

    private fun validateData(
        shoeName: String,
        comapnyName: String,
        showSize: String,
        soheDesc: String
    ): Boolean {
        if (shoeName.isBlank()) {
            _nameError.value = "can not be blank"
            return false
        } else {
            _nameError.value = null
        }
        if (comapnyName.isBlank()) {
            _companyError.value = "can not be blank"
            return false
        } else {
            _companyError.value = null
        }
        if (showSize.isBlank()) {
            _sizeError.value = "can not be blank"
            return false
        } else {
            if (showSize.toDouble().roundToInt() !in MIN_SHOE_SIZE..MAX_SHOE_SIZE) {
                _sizeError.value = String.format(
                    "valid sizes between %d and %d only", MIN_SHOE_SIZE,
                    MAX_SHOE_SIZE
                )
                return false
            } else {
                _sizeError.value = null
            }
        }
        if (soheDesc.isNotBlank() && soheDesc.length > MAX_LENGTH) {
            _descError.value = String.format(
                "Description charaters should not exceed %d characters",
                MAX_LENGTH
            )
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
        _shoe.value = Shoe()
        shoeName = ""
        shoeDesc = ""
        shoeSize = ""
        comapnyName = ""
    }
}