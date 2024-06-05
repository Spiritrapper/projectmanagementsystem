package com.zosh.controller;

import com.zosh.modal.Issue;
import com.zosh.modal.IssueDTO;
import com.zosh.modal.Project;
import com.zosh.modal.User;
import com.zosh.request.IssueRequest;
import com.zosh.response.MessageResponse;
import com.zosh.service.IssueService;
import com.zosh.service.ProjectService;
import com.zosh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/{issueId}")
    public ResponseEntity<Long> getIssueById(@PathVariable Long issueId) throws Exception {
        return ResponseEntity.ok(issueService.getIssueById(issueId).getId());
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Issue>> getIssuesByProjectId(@PathVariable Long projectId) throws Exception {
        return ResponseEntity.ok(issueService.getIssueByProjectId(projectId));
    }

    @PostMapping
    public ResponseEntity<IssueDTO> createIssue(
            @RequestBody IssueRequest issueRequest,

            @RequestHeader("Authorization") String jwt ) throws Exception {
        
        System.out.println("issue----"+issueRequest);
        User tokenUser = userService.findUserProfileByJwt(jwt);
        User user = userService.findUserById(tokenUser.getId());

            // issue에서 프로젝트 ID를 가져올 때 null 체크를 수행합니다.
        if (issueRequest.getProjectId() == null) {
            throw new IllegalArgumentException("Project ID cannot be null");
        }

        // 프로젝트 ID가 유효한지 확인하기 위해 서비스에서 해당 프로젝트를 조회합니다.
        Project project = projectService.getProjectById(issueRequest.getProjectId());
        if (project == null) {
            throw new IllegalArgumentException("Project with ID " + issueRequest.getProjectId() + " not found");
        }


        Issue createIssue = issueService.createIssue(issueRequest, tokenUser);
        IssueDTO issueDTO = new IssueDTO();
        issueDTO.setDescription(createIssue.getDescription());
        issueDTO.setTitle(createIssue.getTitle());
        issueDTO.setDueDate(createIssue.getDueDate());
        issueDTO.setId(createIssue.getId());
        issueDTO.setProject(createIssue.getProject());
        //issueDTO.setProjectId(createIssue.getProjectId());
        issueDTO.setTags(createIssue.getTags());
        issueDTO.setStatus(createIssue.getStatus());
        issueDTO.setAssignee(createIssue.getAssignee());
        issueDTO.setPriority(createIssue.getPriority());
        //issueDTO.setProjectId(createIssue.getProject().getId());




        return ResponseEntity.ok(issueDTO);

    }

    @DeleteMapping("/{issueId}")
    public ResponseEntity<MessageResponse> deleteIssue(
            @PathVariable Long issueId,
            @RequestHeader("Authorization")String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        issueService.deleteIssue(issueId, user.getId());

        MessageResponse res = new MessageResponse();
        res.setMessage("Issue deleted successfully");

        return  ResponseEntity.ok(res);

    }

//    @GetMapping("/search")
//    public ResponseEntity<List<IssueDTO>> searchIssue(
//            @RequestParam(required = false) String title,
//            @RequestParam(required = false) String status,
//            @RequestParam(required = false) String priority,
//            @RequestParam(required = false) Long assigneeId
//
//    )

    @PutMapping("/{issueId}/assignee/{userId}")
    public ResponseEntity<Issue> addUserToIssue(@PathVariable Long issueId, @PathVariable Long userId) throws Exception {
        Issue issue = issueService.addUserToIssue(issueId, userId);

        return ResponseEntity.ok(issue);
    }

    @PutMapping("/{issueId}/status/{status}")
    public ResponseEntity<Issue> updateIssueStatus(@PathVariable Long issueId, @PathVariable String status) throws Exception {
        Issue issue= issueService.updateStatus(issueId,status);
        return  ResponseEntity.ok(issue);
    }

}
