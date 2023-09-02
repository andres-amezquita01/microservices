package authentications.infrastructure.repository

import authentications.domain.repository.AuthenticationRepository
import authentications.domain.entity.User
import agents.domain.entity.Agent
import shared.BaseRepository
import io.getquill._

class AuthenticationRepositoryImpl extends AuthenticationRepository with BaseRepository:

  import ctx._

  override def getUserByUsername(username:String): Option[User] = 
    ctx.run(
      query[User]
      .filter(_.username.getOrElse("") == lift(username))
    ).headOption


  override def createUser(user:User):User =
    ctx.run(
      query[User]
        .insertValue(lift(user))
        .returning(r => r)
    )

  override def getTotalAmountOfUsers(): Long =
    ctx.run(
      query[User].size
    )
  
  override def getUserByEmail(email:String):Option[User] =
    val q = quote {
      for 
        agent <- query[Agent].filter(_.email == lift(email))
        user <- query[User].join(_.agentId == agent.id)
      yield (user)
    }
    ctx.run(q).headOption

  override def getUsers(from: Int, to: Int): List[(User, Agent)] =
    val q = quote {
      for
        user <- query[User]
          .sortBy(_.id)(Ord.ascNullsLast)
          .take(lift(to))
          .drop(lift(from))
        agent <- query[Agent]
          .join(_.id == user.agentId)
      yield(user, agent)
    }
    ctx.run(q)

  override def getUserById(userId: String):Option[(User,Agent)] =
    val q = quote {
      for
        user <- query[User].filter(_.id == lift(userId))
        agent <- query[Agent].join(_.id == user.agentId)
      yield (user, agent)
    }
    ctx.run(q).headOption

  override def getUserByIdentificationCode(identificationCode: String):Option[(User, Agent)] =
    val q = quote {
      for
        agent <- query[Agent].filter(_.identificationCode.getOrElse("") == lift(identificationCode))
        user <- query[User].join(_.agentId == agent.id)
      yield (user, agent)
    }
    ctx.run(q).headOption
