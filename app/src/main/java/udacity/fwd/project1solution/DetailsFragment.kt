package udacity.fwd.project1solution

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import udacity.fwd.project1solution.databinding.FragmentDetailsBinding
import udacity.fwd.project1solution.ui.enums.ShoeDataError
import udacity.fwd.project1solution.ui.viewmodels.MAX_LENGTH
import udacity.fwd.project1solution.ui.viewmodels.MAX_SHOE_SIZE
import udacity.fwd.project1solution.ui.viewmodels.MIN_SHOE_SIZE
import udacity.fwd.project1solution.ui.viewmodels.ShoeViewModel


class DetailsFragment : Fragment() {
    val viewModel by activityViewModels<ShoeViewModel>()
    lateinit var binding: FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.shoe = viewModel.shoe //.shoe.value

        viewModel.addError.observe(viewLifecycleOwner, Observer {
            resetErrors()

            getErrorMessageString(it)
        })


        viewModel.added.observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToShoeListFragment())
                viewModel.onAddComplete()
            }
        })

        binding.cancelBtn.setOnClickListener {
            findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToShoeListFragment())
            viewModel.onAddCanceled()
        }

        binding.saveBtn.setOnClickListener { viewModel.addShoe() }
        return binding.root
    }

    private fun resetErrors() {
        val editTexts = setOf(
            binding.shoeNameText,
            binding.companyNameText,
            binding.shoeSizeText,
            binding.shoeDescText
        )
        for (et in editTexts) {
            et.error = null
        }
    }

    private fun getErrorMessageString(err: ShoeDataError?) {
        if (err == null) return resetErrors()
        return when (err) {
            ShoeDataError.NAME_CAN_NOT_BE_BLANK -> binding.shoeNameText.error =
                getString(R.string.can_not_be_blank_error)
            ShoeDataError.COMPANY_CAN_NOT_BE_BLANK -> binding.companyNameText.error =
                getString(R.string.can_not_be_blank_error)
            ShoeDataError.SIZE_CAN_NOT_BE_BLANK -> binding.shoeSizeText.error =
                getString(R.string.can_not_be_blank_error)
            ShoeDataError.DESC_LARGE_TEXT -> binding.shoeDescText.error =
                getString(R.string.text_large_error, MAX_LENGTH)
            ShoeDataError.SIZE_NOT_IN_ALLOWED_RANGE -> binding.shoeSizeText.error = getString(
                R.string.incorrect_size_error,
                MIN_SHOE_SIZE, MAX_SHOE_SIZE
            )
        }
    }


    override fun onStop() {
        super.onStop()
        viewModel.resetShoe()
    }


}