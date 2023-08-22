package shared

import io.getquill._
import io.getquill.context.jdbc.JdbcContext

import authentications.domain.entity.User
import agents.domain.entity.Agent
import roles.domain.entity._
import permissions.domain.entity.Permission

object BaseRepository:
  lazy val postgresConnection = new PostgresJdbcContext(SnakeCase, "productionDatabase")

trait BaseRepository:

  val ctx = BaseRepository.postgresConnection 

  implicit val userTableName:SchemaMeta[User] = 
    schemaMeta[User]("users")
  implicit val excludeUserInsert:InsertMeta[User] =
    insertMeta[User](_.id)
  implicit val excludeUserUpdate:UpdateMeta[User] = 
    updateMeta[User](_.id)

  implicit val agentsTableName:SchemaMeta[Agent] = 
    schemaMeta[Agent]("agents")

  implicit val roleTableName:SchemaMeta[Role] = 
    schemaMeta[Role]("roles")
  implicit val roleInsert:InsertMeta[Role] =
    insertMeta[Role](_.id)
  implicit val roleUpdate:UpdateMeta[Role] = 
    updateMeta[Role](_.id)

  implicit val permissionTableName:SchemaMeta[Permission] =
    schemaMeta[Permission]("permissions")
  implicit val permissionInsert:InsertMeta[Permission] =
    insertMeta[Permission](_.id)
  implicit val permissionUpdate:UpdateMeta[Permission] =
    updateMeta[Permission](_.id)

  implicit val userRoleTableName:SchemaMeta[UserRole] = 
    schemaMeta[UserRole]("user_roles")
