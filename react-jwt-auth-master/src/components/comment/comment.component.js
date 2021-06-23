import React, { Component } from "react";
import { FaUserCircle } from "react-icons/fa";
import moment from "moment";
import CommentRate from "../rate/comment-rate.component";
import { AiFillLike } from "react-icons/ai";
import RateService from "../../services/rate.service";
import ErrorPage from "../error/error-page.component";

export default class Comment extends Component {

  constructor(props) {
    super(props);
    this.state = {
      currentUserId: this.props.currentUserId,
      currentUserName: this.props.currentUserName,
      commentId: this.props.comment.commentId,
      value: this.props.comment.commentValue,
      userName: this.props.comment.userName,
      points: this.props.comment.points,
      createdDate: this.props.comment.createdDate
    };
  }

  componentWillMount(){
    RateService.getUserCommentRate(this.state.commentId, this.state.currentUserId).then(
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

  render() {
    return (
      <div className="text-center">
        <div className="container py-3 text-center rounded bg-light border">
            <h5 className="container text-center">{this.state.points}&nbsp;<AiFillLike/></h5>
            <small className="float-right text-muted">{moment(this.state.createdDate).format("DD-MM-YYYY hh:mm")}&nbsp;&nbsp;&nbsp;&nbsp;</small>
            <h6 className="float-left text-muted">&nbsp;&nbsp;&nbsp;&nbsp;<FaUserCircle/>&nbsp;&nbsp;{this.state.userName}</h6>
            <br/>
            <br/>
            <span className="container py-3 text-center">{this.state.value}</span>
            <br/>
            <br/>
            {(this.state.currentUserName !== this.state.userName) ? (
              (this.state.alreadyVoted) ? (
                <div className="alert text-center alert-info">
                  You have already rated this Comment!
                </div>
              ) : (
                <div className="container py-3 text-center">
                  <CommentRate dataFromParent={this.state}/>
                </div>
              )) : (null)}
        </div>
        <p/>
      </div>
    );
  }
}
