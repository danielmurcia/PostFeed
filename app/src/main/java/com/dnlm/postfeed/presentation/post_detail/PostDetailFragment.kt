package com.dnlm.postfeed.presentation.post_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dnlm.postfeed.R
import com.dnlm.postfeed.domain.entities.Post
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class PostDetailFragment : Fragment(), OnMapReadyCallback {

    companion object {
        const val POST_ARG = "POST_ARG"
    }

    private val post: Post?
        get() = (arguments?.getSerializable(POST_ARG)) as Post?

    lateinit var mapView: MapView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_post_detail, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.rv_comments)
        val adapter: PostCommentsAdapter = PostCommentsAdapter()

        mapView = root.findViewById<MapView>(R.id.map)

        root.findViewById<TextView>(R.id.tv_title).text = post?.title ?: ""
        root.findViewById<TextView>(R.id.tv_author).text = post?.author?.name ?: ""
        root.findViewById<TextView>(R.id.tv_email).text = post?.author?.email ?: ""
        root.findViewById<TextView>(R.id.tv_website).text = post?.author?.website ?: ""
        root.findViewById<TextView>(R.id.tv_phone).text = post?.author?.phone ?: ""
        root.findViewById<TextView>(R.id.tv_body).text = post?.body ?: ""

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        adapter.comments = post?.comments ?: listOf()

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        return root
    }

    override fun onMapReady(map: GoogleMap) {
        val address = post?.author?.address
        address?.geo?.let {
            val location = LatLng(it.lat.toDouble(), it.lng.toDouble())
            map.addMarker(
                MarkerOptions()
                    .position(location)
                    .title("${address.street} - ${address.city}")
            )
            map.moveCamera(CameraUpdateFactory.newLatLng(location));
        }
    }
}