package udacity.fwd.project1solution

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import udacity.fwd.project1solution.databinding.FragmentDetailsBinding

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentDetailsBinding>(inflater,R.layout.fragment_details, container, false)

        binding.cancelBtn.setOnClickListener {
            findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToShoeListFragment())
        }

        binding.saveBtn.setOnClickListener {
            if (validateData(binding))
            {
                val sName = binding.shoeNameText.text.toString()
                val scomp = binding.companyNameText.text.toString()
                val sSize = binding.shoeSizeText.text.toString().toInt()
                val sDesc = binding.shoeDescText.text.toString()

                findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToShoeListFragment(sName,scomp,sSize,sDesc))
            }
        }
        return binding.root
    }

    private fun validateData(binding: FragmentDetailsBinding): Boolean {
        if (binding.shoeNameText.text.isNullOrBlank()){
            binding.shoeNameText.error="can not be blank"
            return false
        }else{
            binding.shoeNameText.error=null
        }

        if (binding.companyNameText.text.isNullOrBlank()){
            binding.companyNameText.error="can not be blank"
            return false
        }
        else{
            binding.companyNameText.error=null
        }

        if (binding.shoeSizeText.text.isNullOrBlank()){
            binding.shoeSizeText.error="can not be blank"
            return false
        }else if ( binding.shoeSizeText.text.toString().toInt()   !in 30..60 ){
            binding.shoeSizeText.error="valid sizes between 30 and 60 only"
            return false
        } else{
            binding.shoeSizeText.error=null
        }

        return true
    }


}