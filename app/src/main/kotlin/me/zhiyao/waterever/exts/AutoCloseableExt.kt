package me.zhiyao.waterever.exts

/**
 *
 * @author WangZhiYao
 * @date 2020/9/13
 */
fun AutoCloseable.extClose() {
    try {
        this.close()
    } catch (ignore: Exception) {

    }
}