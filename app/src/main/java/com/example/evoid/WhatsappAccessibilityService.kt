package com.example.evoid

import android.view.accessibility.AccessibilityEvent

public class WhatsappAccessibilityService : android.accessibilityservice.AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (rootInActiveWindow == null)
        {
            return
        }

    }

    override fun onInterrupt() {
        TODO("Not yet implemented")
    }
}