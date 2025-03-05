package br.com.users.users.mapper

import br.com.users.users.data.vo.v2.UserVO
import br.com.users.users.models.UserModel
import com.github.dozermapper.core.DozerBeanMapperBuilder
import com.github.dozermapper.core.Mapper
import org.springframework.stereotype.Service
import java.util.Date

@Service
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

    fun mapEntityToVO(user: UserModel) : UserVO{
        val uVO: UserVO = UserVO()
        uVO.id = user.id
        uVO.name = user.name
        uVO.date = Date()
        return uVO
    }

    fun mapVOtoEntity(user: UserVO) : UserModel{
        val u: UserModel = UserModel()
        u.id = user.id
        u.name = user.name
        return u
    }
}