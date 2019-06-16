package de.andi95.crudservicespringdata

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.created
import org.springframework.http.ResponseEntity.noContent
import org.springframework.http.ResponseEntity.notFound
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
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
        return created(uri).body(savedConference)
    }

    @GetMapping("/{id}")
    fun `read single element`(@PathVariable id: String) :ResponseEntity<Conference> {
        val conference = conferenceRepository.findById(id)
        return if (conference.isPresent) {
            ok(conference.get())
        } else notFound().build()
    }

    @PutMapping("/{id}")
    fun `update single element`(@PathVariable id: String, @RequestBody conference: Conference) :ResponseEntity<Conference> {
        val oldConference = conferenceRepository.findById(id)
        val savedConference = conferenceRepository.save(conference)
        return if (oldConference.isPresent) {
            ok(savedConference)
        } else {
            val uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedConference.id)
                    .toUri()
            created(uri).body(savedConference)
        }
    }

    @DeleteMapping("/{id}")
    fun `delete single element`(@PathVariable id: String) :ResponseEntity<HttpStatus> {
        val conference = conferenceRepository.findById(id)
        return if (conference.isPresent) {
            conferenceRepository.delete(conference.get())
            noContent().build()
        } else notFound().build()

    }

}