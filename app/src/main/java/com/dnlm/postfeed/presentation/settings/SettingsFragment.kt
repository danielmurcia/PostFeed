package com.dnlm.postfeed.presentation.settings

import SettingViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dnlm.postfeed.R

class SettingsFragment : Fragment() {

    private lateinit var settingsViewModel: SettingViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        settingsViewModel =
            ViewModelProvider(this)[SettingViewModel::class.java]

        val root = inflater.inflate(R.layout.fragment_settings, container, false)
        val etEmail: EditText = root.findViewById(R.id.et_email)

        return root
    }
}