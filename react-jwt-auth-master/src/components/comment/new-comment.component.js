import React, { Component } from "react";
import CommentService from "../../services/comment.service";
import Form from "react-validation/build/form";
import Textarea from "react-validation/build/textarea";
import CheckButton from "react-validation/build/button";

const required = value => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};

const vcomment = value => {
  if (value.length < 1) {
    return (
      <div className="alert alert-danger" role="alert">
        The comment can not be empty.
      </div>
    );
  }
};

export default class NewComment extends Component {

  constructor(props) {
    super(props);
    this.onChangeComment = this.onChangeComment.bind(this);
    this.handleCreateComment = this.handleCreateComment.bind(this);

    this.state = {
      currentUser: this.props.dataFromParent.currentUser,
      userReady: this.props.dataFromParent.userReady,
      blogId: this.props.dataFromParent.blogId,
      loading: false,
      error: "",
      commentValue: ""
    };

  }

  onChangeComment(e) {
    console.log(e.target.commentValue);
    this.setState({
      commentValue: e.target.value
    });
  }

  handleCreateComment(e) {

    e.preventDefault();

    this.setState({
      message: "",
      successful: false
    });

    this.form.validateAll();

    console.log(this.state);
    if (this.checkBtn.context._errors.length === 0) {
      console.log("Entra1");
      CommentService.createComment(
        this.state.currentUser.id,
        this.state.blogId,
        this.state.commentValue
      ).then(
        response => {
          if (response.status === 201) {
            window.location.reload();
          }
        },
        error => {
          const resMessage =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();

          this.setState({
            successful: false,
            message: resMessage
          });
        }
      );
    }
  }

  renderError() {
    return this.state.error ? (
      <div className="alert alert-danger">{this.state.error}</div>
    ) : null;
  }

  render() {
    return (
      <React.Fragment>
          <Form onSubmit={this.handleCreateComment}
            ref={c => {
              this.form = c;
            }}>
            {!this.state.successful && (
              <div className="form-group">
                <h5 htmlFor="comment">New comment</h5>
                <Textarea
                  type="textarea"
                  className="form-control"
                  name="comment"
                  value={this.state.commentValue}
                  onChange={this.onChangeComment}
                  validations={[required, vcomment]}
                />
                <p/>
                <div className="form-group">
                  <button className="btn btn-primary">Comment ➤</button>
                </div>
              </div>
            )}
            {this.state.message && (
              <div className="form-group">
                <div className={
                    this.state.successful
                      ? "alert alert-success"
                      : "alert alert-danger"
                  }
                  role="alert">
                  {this.state.message}
                </div>
              </div>
            )}
            <CheckButton
              style={{ display: "none" }}
              ref={c => {
                this.checkBtn = c;
              }}
            />
          </Form>
      </React.Fragment>
    );
  }
}
