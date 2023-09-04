package shared

import io.getquill._
import io.getquill.context.jdbc.JdbcContext

import authentications.domain.entity.User
import agents.domain.entity.Agent
import roles.domain.entity._
import permissions.domain.entity.Permission
import com.zaxxer.hikari.HikariDataSource
import org.postgresql.ds.PGSimpleDataSource
import com.zaxxer.hikari.HikariConfig
import scala.util.Properties

object BaseRepository:
  lazy val config = new HikariConfig() {{ 
    setDataSource(new PGSimpleDataSource {{
      setServerNames(Array(Properties.envOrElse("DB_URL", "localhost" )))
      setPortNumbers(Array(Properties.envOrElse("DB_PORT", "5432").toInt))
      setDatabaseName(Properties.envOrElse("DB_NAME", "authentication"))
      setUser(Properties.envOrElse("DB_USER", "postgres"))
      setPassword(Properties.envOrElse("DB_PASSWORD", "totally_secure_password"))
    }})
  }}

  lazy val postgresConnection = new PostgresJdbcContext(
    naming = SnakeCase, 
    new HikariDataSource(config)
  )

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
