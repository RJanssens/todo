package com.projectmanager.app.config;

import com.projectmanager.app.model.*;
import com.projectmanager.app.model.Ticket.TicketPriority;
import com.projectmanager.app.model.Ticket.TicketStatus;
import com.projectmanager.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void run(String... args) throws Exception {
        // Create sample users
        User john = new User();
        john.setUsername("john_doe");
        john.setEmail("john@example.com");
        john.setFullName("John Doe");
        john.setAvatarUrl("https://i.pravatar.cc/150?img=1");
        john = userRepository.save(john);

        User jane = new User();
        jane.setUsername("jane_smith");
        jane.setEmail("jane@example.com");
        jane.setFullName("Jane Smith");
        jane.setAvatarUrl("https://i.pravatar.cc/150?img=2");
        jane = userRepository.save(jane);

        User bob = new User();
        bob.setUsername("bob_wilson");
        bob.setEmail("bob@example.com");
        bob.setFullName("Bob Wilson");
        bob.setAvatarUrl("https://i.pravatar.cc/150?img=3");
        bob = userRepository.save(bob);

        // Create sample projects
        Project webProject = new Project();
        webProject.setName("Web Application");
        webProject.setKey("WEB");
        webProject.setDescription("Main web application project");
        webProject.setOwner(john);
        webProject = projectRepository.save(webProject);

        Project mobileProject = new Project();
        mobileProject.setName("Mobile App");
        mobileProject.setKey("MOB");
        mobileProject.setDescription("Mobile application for iOS and Android");
        mobileProject.setOwner(jane);
        mobileProject = projectRepository.save(mobileProject);

        // Create sample tags
        Tag bugTag = new Tag();
        bugTag.setName("bug");
        bugTag.setColor("#ef4444");
        bugTag = tagRepository.save(bugTag);

        Tag featureTag = new Tag();
        featureTag.setName("feature");
        featureTag.setColor("#10b981");
        featureTag = tagRepository.save(featureTag);

        Tag enhancementTag = new Tag();
        enhancementTag.setName("enhancement");
        enhancementTag.setColor("#3b82f6");
        enhancementTag = tagRepository.save(enhancementTag);

        Tag urgentTag = new Tag();
        urgentTag.setName("urgent");
        urgentTag.setColor("#f59e0b");
        urgentTag = tagRepository.save(urgentTag);

        // Create sample tickets for Web Application
        Ticket ticket1 = new Ticket();
        ticket1.setTitle("Implement user authentication");
        ticket1.setDescription("Add JWT-based authentication system for secure login");
        ticket1.setTicketNumber("WEB-1");
        ticket1.setStatus(TicketStatus.IN_PROGRESS);
        ticket1.setPriority(TicketPriority.HIGH);
        ticket1.setProject(webProject);
        ticket1.setReporter(john);
        ticket1.setAssignee(jane);
        ticket1.setDueDate(LocalDate.now().plusDays(7));
        ticket1.getTags().add(featureTag);
        ticket1.getTags().add(urgentTag);
        ticket1 = ticketRepository.save(ticket1);

        Ticket ticket2 = new Ticket();
        ticket2.setTitle("Fix navigation menu bug");
        ticket2.setDescription("Navigation menu doesn't work properly on mobile devices");
        ticket2.setTicketNumber("WEB-2");
        ticket2.setStatus(TicketStatus.TODO);
        ticket2.setPriority(TicketPriority.MEDIUM);
        ticket2.setProject(webProject);
        ticket2.setReporter(jane);
        ticket2.setAssignee(bob);
        ticket2.setDueDate(LocalDate.now().plusDays(3));
        ticket2.getTags().add(bugTag);
        ticket2 = ticketRepository.save(ticket2);

        Ticket ticket3 = new Ticket();
        ticket3.setTitle("Add dark mode support");
        ticket3.setDescription("Implement dark mode theme for better user experience");
        ticket3.setTicketNumber("WEB-3");
        ticket3.setStatus(TicketStatus.READY_FOR_REVIEW);
        ticket3.setPriority(TicketPriority.LOW);
        ticket3.setProject(webProject);
        ticket3.setReporter(bob);
        ticket3.setAssignee(jane);
        ticket3.getTags().add(enhancementTag);
        ticket3 = ticketRepository.save(ticket3);

        Ticket ticket4 = new Ticket();
        ticket4.setTitle("Optimize database queries");
        ticket4.setDescription("Improve performance by optimizing slow database queries");
        ticket4.setTicketNumber("WEB-4");
        ticket4.setStatus(TicketStatus.DONE);
        ticket4.setPriority(TicketPriority.HIGH);
        ticket4.setProject(webProject);
        ticket4.setReporter(john);
        ticket4.setAssignee(john);
        ticket4.getTags().add(enhancementTag);
        ticket4 = ticketRepository.save(ticket4);

        // Create sample tickets for Mobile App
        Ticket ticket5 = new Ticket();
        ticket5.setTitle("Design splash screen");
        ticket5.setDescription("Create an attractive splash screen for app launch");
        ticket5.setTicketNumber("MOB-1");
        ticket5.setStatus(TicketStatus.TODO);
        ticket5.setPriority(TicketPriority.MEDIUM);
        ticket5.setProject(mobileProject);
        ticket5.setReporter(jane);
        ticket5.setAssignee(bob);
        ticket5.setDueDate(LocalDate.now().plusDays(5));
        ticket5.getTags().add(featureTag);
        ticket5 = ticketRepository.save(ticket5);

        Ticket ticket6 = new Ticket();
        ticket6.setTitle("Implement push notifications");
        ticket6.setDescription("Add push notification support for iOS and Android");
        ticket6.setTicketNumber("MOB-2");
        ticket6.setStatus(TicketStatus.IN_PROGRESS);
        ticket6.setPriority(TicketPriority.HIGHEST);
        ticket6.setProject(mobileProject);
        ticket6.setReporter(jane);
        ticket6.setAssignee(john);
        ticket6.setDueDate(LocalDate.now().plusDays(10));
        ticket6.getTags().add(featureTag);
        ticket6.getTags().add(urgentTag);
        ticket6 = ticketRepository.save(ticket6);

        // Create sample comments
        Comment comment1 = new Comment();
        comment1.setTicket(ticket1);
        comment1.setAuthor(john);
        comment1.setContent("I've started working on this. Will use Spring Security with JWT tokens.");
        commentRepository.save(comment1);

        Comment comment2 = new Comment();
        comment2.setTicket(ticket1);
        comment2.setAuthor(jane);
        comment2.setContent("Sounds good! Let me know if you need any help with the frontend integration.");
        commentRepository.save(comment2);

        Comment comment3 = new Comment();
        comment3.setTicket(ticket2);
        comment3.setAuthor(bob);
        comment3.setContent("I've identified the issue. It's related to CSS media queries. Will fix it today.");
        commentRepository.save(comment3);

        System.out.println("Sample data loaded successfully!");
    }
}
