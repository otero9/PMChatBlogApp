import React, { Component } from "react";
import BlogService from "../../services/blog.service";
import { AiFillLike } from "react-icons/ai";
import Comment from "../comment/comment.component";
import NewComment from "../comment/new-comment.component";
import BlogRate from "../rate/blog-rate.component";
import ErrorPage from "../error/error-page.component";
import RateService from "../../services/rate.service";
import UserService from "../../services/user.service";
import { Link } from "react-router-dom";
import { Button } from "react-bootstrap";
import { confirmAlert } from "react-confirm-alert";
import "react-confirm-alert/src/react-confirm-alert.css";


export default class Blog extends Component {

  constructor(props) {
    super(props);
    this.renderViewUser = this.renderViewUser.bind(this);

    this.state = {
        currentUser: undefined,
        userReady: false,
        blogId: this.props.match.params.id,
        blog: "",
        comments: [],
        alreadyVoted: false,
        userLink: undefined
    };
  }

  updateBlog = () =>{
    this.props.history.push("/updateBlog/"+this.state.blogId);
    window.location.reload();
  }

  renderViewUser(userId,userName) {
    const urlViewUser = "/user/"+userId;
    return <Link to={urlViewUser}>{userName}</Link>;
  }

  deleteBlog = () =>{
    confirmAlert({
      title: 'Confirm Blog deletion',
      message: 'Are you sure to delete your Blog, their ratings and their comments?',
      buttons: [
        {
          label: 'Yes',
          onClick: () => BlogService.deleteBlog(this.state.blogId).then(
            (response) => {
              this.props.history.push("/myBlogs");
              window.location.reload();
            },
            error => {
              const resMessage = UserService.logout();
                (error.response &&
                  error.response.data &&
                  error.response.data.message) ||
                error.message ||
                error.toString();

              this.setState({
                loading: false,
                message: resMessage
              });
            }
          )
        },
        {
          label: 'No',
          onClick: () => window.location.reload()
        }
      ]
    });
  }

  componentWillMount(){
    BlogService.getBlogById(this.state.blogId).then(
      response => {
        if (response.status === 200) {
          this.setState({
            blog: response.data,
            userLink: this.renderViewUser(response.data.userId,response.data.userName)
          });
          if(this.state.blog.comments !== undefined) {
            this.setState({
              comments: this.state.blog.comments.map( comment => ({ commentId: comment.commentId, commentValue: comment.commentValue, userName: comment.userName, createdDate: comment.createdDate, points: comment.points }))
            });
          }
        }
        if (response.status !== 200) {
          return <ErrorPage/>;
        }
      },
      error => {
        this.props.history.push("/error");
        window.location.reload();
        this.setState({
          content:
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString()
        });
      }
    );
    const user = UserService.getCurrentUser();

    if (user) {
      this.setState({
        currentUser: user,
        userReady: true
      });

      RateService.getUserBlogRate(this.state.blogId, user.id).then(
        response => {
          if (response.status === 200) {
            if (response.data.value !== 0) {
              this.setState({
                alreadyVoted: true
              });
            }
          }
          if (response.status !== 200) {
            return <ErrorPage/>;
          }
        },
        error => {
          this.props.history.push("/error");
          window.location.reload();
          this.setState({
            content:
              (error.response &&
                error.response.data &&
                error.response.data.message) ||
              error.message ||
              error.toString()
          });
        }
      );
    }
  }

  render() {
    return (
      <div className="container">
        {(this.state.userReady) ? (
            (this.state.currentUser.id === this.state.blog.userId) ? (
              <div>
                <p/>
                <div className="jumbotron py-3 text-center">
                    <Button variant="primary" onClick={this.updateBlog} size="small">Update blog</Button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <Button variant="danger" onClick={this.deleteBlog} size="small">Delete blog</Button>
                </div>
              </div>
            ) : (null)
        ) : (null)}
        <p/>
        <header className="jumbotron py-3 text-center">
          <h5>{this.state.blog.points}&nbsp;<AiFillLike/></h5>
          <h2>
            <strong>{this.state.blog.title}</strong>
          </h2>
          <hr/>
          <h8>{this.state.userLink}</h8>
          <h6>{this.state.blog.header}</h6>
        </header>
        {this.state.blog.body}
        <hr/>
        <footer className="page-footer py-3 text-center">
                {this.state.blog.footer}
        </footer>
        <hr/>
        <h5 className="text-muted mb-4 text-center">
          <span className="badge badge-success">{this.state.comments.length}</span>&nbsp;
          Comment{this.state.comments.length > 0 ? "s" : ""}
        </h5>
        <hr/>
        {(this.state.userReady) ? (
          <div className="container py-3 text-center">
              {(this.state.userReady && (this.state.currentUser.id !== this.state.blog.userId)) ? (
                (this.state.alreadyVoted) ? (
                  <div className="alert text-center alert-info">
                    You have already rated this Blog!
                  </div>
                ) : (
                  <div className="container py-3 text-center">
                    <BlogRate dataFromParent = {this.state}/>
                  </div>
                )
              ) : (
                <div className="alert text-center alert-info">
                  You can not rate your own Blog!
                </div>
              )}
            <hr/>
            {this.state.comments.length === 0 ? (
              <div className="alert text-center alert-info">
                Be the first to comment
              </div>
            ) : null}
            <br/>
            <NewComment dataFromParent = {this.state}/>
          </div>
        ) : (null)}
        <hr/>
        <div className="commentList">
          {this.state.comments.map((comment, index) => (
            <Comment key={index} comment={comment} currentUserName={this.state.currentUser.username} currentUserId={this.state.currentUser.id} />
          ))}
        </div>
        <p/>
      </div>
    );
  }

}
