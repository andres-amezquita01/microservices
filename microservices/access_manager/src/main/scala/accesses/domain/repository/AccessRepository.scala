package access.domain.repository

import accesses.domain.entity.Access
import java.util.UUID

trait AccessRepository:
  def getAccess(id: UUID): Option[Access]
  def insertAccess(access: Access): Access
  def updateAccess(access: Access): Access
  def removeAccess(id: UUID): Access
