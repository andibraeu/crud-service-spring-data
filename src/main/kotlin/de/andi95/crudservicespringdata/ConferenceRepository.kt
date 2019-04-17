package de.andi95.crudservicespringdata

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ConferenceRepository : CrudRepository<Conference, String>