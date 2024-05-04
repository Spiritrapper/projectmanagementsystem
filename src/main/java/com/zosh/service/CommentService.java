package com.zosh.service;

import com.zosh.modal.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Long issueId, Long userId, String Content) throws Exception;

    void deleteComment( Long commentId, Long userId) throws Exception;

    List<Comment> findCommentByIssueId(Long issueId);
}
