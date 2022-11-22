package com.example.gm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.example.gm.model.Contact
import com.example.gm.view.components.ContactProfile


class ContactFragment : Fragment() {

    private var contact : Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contact =arguments?.getSerializable("contact") as Contact

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                contact?.let { ContactProfile(it) }
                }
            }
        }
}
