package com.example.zoomplus.models

import android.graphics.Bitmap

class ChatMessage(val id: String, val text: String, val fromId: String, val toId: String, val timestamp: Long, val bitmap: Bitmap?) {
    constructor() : this(
        "",
        "",
        "",
        "",
        -1,
        null
    )
}