package me.zhiyao.waterever.exts

import java.util.*

/**
 *
 * @author WangZhiYao
 * @date 2020/9/12
 */
fun UUID.short(): String {
    val uuid = this.toString().split("-")
    return uuid[uuid.size - 1]
}