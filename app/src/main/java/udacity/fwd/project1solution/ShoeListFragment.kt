package udacity.fwd.project1solution

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import timber.log.Timber
import udacity.fwd.project1solution.databinding.FragmentShoeListBinding
import udacity.fwd.project1solution.databinding.ListItemBinding
import udacity.fwd.project1solution.models.Shoe
import udacity.fwd.project1solution.ui.viewmodels.ShoeViewModel

/*
 * A simple [Fragment] subclass.
 * Use the [ShoeListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeListFragment : Fragment() {

    val viewModel by activityViewModels<ShoeViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentShoeListBinding>(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false
        )

        binding.viewModel = viewModel
        binding.goDetailsFab.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToDetailsFragment())
        }

        viewModel.shoeList.observe(viewLifecycleOwner, Observer {
            addShoesToView(binding, it)
        })


        setHasOptionsMenu(true)
        return binding.root
    }

    private fun addShoesToView(binding: FragmentShoeListBinding, list: List<Shoe>) {
        Timber.i(list.toString())

        binding.apply {
//            listLayout.removeAllViews()
            for (shoe in list) {
//            if (list.isNotEmpty()) {
//                val shoe = list.last()
                val tv = DataBindingUtil.inflate<ListItemBinding>(
                    layoutInflater,
                    R.layout.list_item,
                    null,
                    false
                )
//                    View.inflate(this@ShoeListFragment.activity, R.layout.list_item, null)
                tv.nameTextView.text = shoe.name
                tv.companyTextView.text = shoe.company
                tv.sizeTextView.text = shoe.size.toString()
//                val tv_binding = DataBindingUtil.inflate<FragmentShoeListBinding>(
//                    inflater,
//                    R.layout.fragment_shoe_list,
//                    container,
//                    false
//                )

//                tv.text = shoe.name
                listLayout.addView(tv.root)
//            }
            }
        }

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.menu_logout -> {
                findNavController().navigate(ShoeListFragmentDirections.actionGraphShoeListFragmentToGraphLoginFragment())
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}