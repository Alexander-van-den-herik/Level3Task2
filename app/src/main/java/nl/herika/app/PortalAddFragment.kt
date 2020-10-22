package nl.herika.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_portal_add.*
import nl.herika.app.data.Portal


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class PortalAddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portal_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_add_portal.setOnClickListener {
            onAddPortal()
        }
    }

    private fun onAddPortal() {
        val portalTitle = et_title.text.toString()
        val portalUrl = et_url.text.toString()

        if (portalTitle.isNotBlank() and portalUrl.isNotBlank()) {
            setFragmentResult(
                PORTAL,
                bundleOf(
                    PORTAL to Portal(portalTitle, portalUrl)
                )
            )

            findNavController().popBackStack()
        } else {
            Toast.makeText(
                activity,
                R.string.invalid_portal,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
