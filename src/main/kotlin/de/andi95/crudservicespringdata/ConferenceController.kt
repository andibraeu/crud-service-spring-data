package de.andi95.crudservicespringdata

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/conferences")
class ConferenceController(@Autowired val conferenceRepository: ConferenceRepository) {

    @PostMapping
    fun `create new element`(@RequestBody conference: Conference) :ResponseEntity<Conference> {
        val savedConference = conferenceRepository.save(conference)
        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedConference.id)
                .toUri()
        return ResponseEntity.created(uri).body(savedConference)
    }

}