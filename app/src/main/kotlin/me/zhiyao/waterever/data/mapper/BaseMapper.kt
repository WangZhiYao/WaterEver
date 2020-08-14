package me.zhiyao.waterever.data.mapper

/**
 *
 * @author WangZhiYao
 * @date 2020/8/14
 */
interface BaseMapper<I, O> {

    fun map(input: I): O
}