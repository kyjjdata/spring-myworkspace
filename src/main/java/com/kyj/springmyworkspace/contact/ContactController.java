package com.kyj.springmyworkspace.contact;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

	private ContactRepository repo;

	@Autowired
	public ContactController(ContactRepository repo) {
		this.repo = repo;
	}

	@GetMapping(value = "/contacts")
	public List<Contact> getContactList() {
		// SELECT * FROM contact ORDER BY id DESC; -- ����(descending) ����
		return repo.findAll(Sort.by("id").descending());
	}

	@GetMapping(value = "/contacts/paging")
	public Page<Contact> getContactListPaging(@RequestParam int page, @RequestParam int size) {
		return repo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
	}

	@PostMapping(value = "/contacts")
	public Contact addContact(@RequestBody Contact contact, HttpServletResponse res) {
		if (contact.getName() == null || contact.getName().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		if (contact.getPhone() == null || contact.getPhone().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		if (contact.getEmail() == null || contact.getEmail().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		contact.setCreatedTime(new Date().getTime());

		return repo.save(contact);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/contacts/{id}")
	public Contact getContact(@PathVariable int id, HttpServletResponse res) {
		Optional<Contact> contact = repo.findById(id);
		if (contact.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		return contact.get();
	}

	@DeleteMapping(value = "/contacts/{id}")
	public boolean removeContact(@PathVariable int id, HttpServletResponse res) {
		Optional<Contact> contact = repo.findById(id);
		if (contact.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return false;
		}
		repo.deleteById(id);
		return true;
	}

	@PutMapping(value = "/contacts/{id}")
	public Contact modifyContact(@PathVariable int id, @RequestBody Contact contact, HttpServletResponse res) {
		Optional<Contact> findedContact = repo.findById(id);
		if (findedContact.isEmpty()) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		if (contact.getName() == null && contact.getName().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		if (contact.getPhone() == null && contact.getPhone().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		if (contact.getEmail() == null && contact.getEmail().equals("")) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		Contact toUpdateContact = findedContact.get();
		toUpdateContact.setName(contact.getName());
		toUpdateContact.setPhone(contact.getPhone());
		toUpdateContact.setEmail(contact.getEmail());

		return repo.save(toUpdateContact);
	}
}
