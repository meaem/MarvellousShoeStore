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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.shoe = viewModel.shoe //.shoe.value

        viewModel.nameError.observe(viewLifecycleOwner, Observer {
            binding.shoeNameText.error = getErrorMessageString(it)
        })

        viewModel.companyError.observe(viewLifecycleOwner, Observer {
            binding.companyNameText.error = getErrorMessageString(it)
        })
        viewModel.sizeError.observe(viewLifecycleOwner, Observer {
            binding.shoeSizeText.error = getErrorMessageString(it)
        })
        viewModel.descError.observe(viewLifecycleOwner, Observer {
            binding.shoeDescText.error = getErrorMessageString(it)
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

    private fun getErrorMessageString(err: ShoeDataError?): String? {
        if (err == null) return null
        return when (err) {
            ShoeDataError.CAN_NOT_BE_BLANK -> getString(R.string.can_not_be_blank_error)
            ShoeDataError.LARGE_TEXT -> getString(R.string.text_large_error, MAX_LENGTH)
            ShoeDataError.NOT_IN_ALLOWED_RANGE -> getString(
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