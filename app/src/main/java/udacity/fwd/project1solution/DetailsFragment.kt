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
import udacity.fwd.project1solution.ui.viewmodels.ShoeViewModel


class DetailsFragment : Fragment() {
    val viewModel by activityViewModels<ShoeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentDetailsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel //.shoe.value

        viewModel.nameError.observe(viewLifecycleOwner, Observer {
            binding.shoeNameText.error = it
        })

        viewModel.companyError.observe(viewLifecycleOwner, Observer {
            binding.companyNameText.error = it
        })
        viewModel.sizeError.observe(viewLifecycleOwner, Observer {
            binding.shoeSizeText.error = it
        })
        viewModel.descError.observe(viewLifecycleOwner, Observer {
            binding.shoeDescText.error = it
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

        binding.saveBtn.setOnClickListener {

//            val sName = binding.shoeNameText.text.toString()
//            val scomp = binding.companyNameText.text.toString()
//            val sSize = binding.shoeSizeText.text.toString()
//            val sDesc = binding.shoeDescText.text.toString()

//            viewModel.addShoe(sName, scomp, sSize, sDesc)
            viewModel.addShoe()

        }
        return binding.root
    }

    override fun onStop() {
        super.onStop()
        viewModel.resetShoe()
    }


}