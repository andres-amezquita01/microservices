package authorizations.domain.repository

import authorizations.domain.entity.PermissionContext
import roles.domain.entity.Role
import java.util.UUID

trait AuthorizationRepository:
  def getPermissionContextOfUser(userId: UUID): PermissionContext 
  def getRolesOfUser(userId: UUID): List[Role]
