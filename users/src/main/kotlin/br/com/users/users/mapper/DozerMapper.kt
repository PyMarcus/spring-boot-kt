package br.com.users.users.mapper

import com.github.dozermapper.core.DozerBeanMapperBuilder
import com.github.dozermapper.core.Mapper

object DozerMapper {
    private val mapper: Mapper = DozerBeanMapperBuilder.buildDefault()

    fun <Origin, Destin> parseObject(_origin: Origin, _destiny: Class<Destin>): Destin{
        return mapper.map(_origin, _destiny)
    }

    fun <Origin, Destin> parseListObject(_origin: List<Origin>, _destiny: Class<Destin>): List<Destin>{
        val listObjects: MutableList<Destin> = ArrayList<Destin>()
        for (o in _origin){
            listObjects.add(mapper.map(o, _destiny))
        }
        return listObjects
    }
}