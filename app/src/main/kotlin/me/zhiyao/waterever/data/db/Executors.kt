package me.zhiyao.waterever.data.db

import java.util.concurrent.Executors

/**
 *
 * @author WangZhiYao
 * @date 2020/8/13
 */
private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

fun ioThread(f: () -> Unit) {
    IO_EXECUTOR.execute(f)
}