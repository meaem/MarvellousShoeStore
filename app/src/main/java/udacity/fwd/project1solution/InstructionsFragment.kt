package udacity.fwd.project1solution

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import udacity.fwd.project1solution.databinding.FragmentInstructionsBinding


class InstructionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding = FragmentInstructionsBinding.inflate(inflater, container, false)
        binding.proceedBtn.setOnClickListener {
            findNavController().navigate(InstructionsFragmentDirections.actionInstructionsFragmentToShoeListFragment())
        }
        return binding.root
//        FragmentInstructionsBinding.
    }

}