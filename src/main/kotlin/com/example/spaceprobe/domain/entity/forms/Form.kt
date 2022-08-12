package com.example.spaceprobe.domain.entity.forms

import com.example.spaceprobe.domain.entity.Position

interface Form {
    fun isOutOfBounds(position: Position): Boolean
}
