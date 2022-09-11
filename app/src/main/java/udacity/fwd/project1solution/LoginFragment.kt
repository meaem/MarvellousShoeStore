package udacity.fwd.project1solution

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import udacity.fwd.project1solution.databinding.FragmentLoginBinding



class LoginFragment : Fragment() {

    //lateinit var binding :LoginF
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.loginBtn.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragment2ToWelcomeFragment())
        }


        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_login, container, false)
        return binding.root
    }


}