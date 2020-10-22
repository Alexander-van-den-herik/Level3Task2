package nl.herika.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_portal_overview.*
import nl.herika.app.data.Portal

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PortalOverviewFragment : Fragment() {

    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals) { portal: Portal ->
        portalClicked(
            portal
        )
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portal_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_portal_cards.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        rv_portal_cards.adapter = portalAdapter

        createItemTouchHelper().attachToRecyclerView(rv_portal_cards)
        observeAddReminderResult()
    }

    private fun portalClicked(portal: Portal) {
        val url = portal.url

        val browserIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    private fun createItemTouchHelper(): ItemTouchHelper {

        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                portals.removeAt(position)
                portalAdapter.notifyDataSetChanged()
            }
        }
        return ItemTouchHelper(callback)
    }

    private fun observeAddReminderResult() {
        setFragmentResultListener(PORTAL) { _, bundle ->
            bundle.getParcelable<Portal>(PORTAL)?.let {
                portals.add(it)
                portalAdapter.notifyDataSetChanged()
            } ?: Log.e("PortalCard", "No PortalCard received!")
        }
    }
}
