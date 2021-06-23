import React, { Component } from "react";
import VideoService from "../../services/video.service";
import { AiFillLike } from "react-icons/ai";
import VideoRate from "../rate/video-rate.component";
import ErrorPage from "../error/error-page.component";
import UserService from "../../services/user.service";
import RateService from "../../services/rate.service";
import { Button } from "react-bootstrap";
import ReactPlayer from "react-player";
import { Link } from "react-router-dom";
import { confirmAlert } from "react-confirm-alert";
import "react-confirm-alert/src/react-confirm-alert.css";
import "video-react/dist/video-react.css";

export default class Video extends Component {

  constructor(props) {
    super(props);
    this.renderViewUser = this.renderViewUser.bind(this);

    this.state = {
        currentUser: undefined,
        userReady: false,
        videoId: this.props.match.params.id,
        video: "",
        alreadyVoted: false,
        userLink: undefined
    };
  }

  updateVideo = () =>{
    this.props.history.push("/updateVideo/"+this.state.videoId);
    window.location.reload();
  }

  deleteVideo = () =>{
    confirmAlert({
      title: 'Confirm Video deletion',
      message: 'Are you sure to delete your Video and their ratings?',
      buttons: [
        {
          label: 'Yes',
          onClick: () => VideoService.deleteVideo(this.state.videoId).then(
            (response) => {
              this.props.history.push("/myVideos");
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

  renderViewUser(userId,userName) {
    const urlViewUser = "/user/"+userId;
    return <Link to={urlViewUser}>{userName}</Link>;
  }

  componentWillMount(){
    VideoService.getVideoById(this.state.videoId).then(
      response => {
        if (response.status === 200) {
          this.setState({
            video: response.data,
            userLink: this.renderViewUser(response.data.userId,response.data.userName)
          });
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

      RateService.getUserVideoRate(this.state.videoId, user.id).then(
        response => {
          if (response.status === 200) {
            console.log(user.id);
            console.log(this.state.videoId);
            console.log(response.data.value);
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
            (this.state.currentUser.id === this.state.video.userId) ? (
              <div>
                <p/>
                <div className="jumbotron py-3 text-center">
                    <Button variant="primary" onClick={this.updateVideo} size="small">Update video</Button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <Button variant="danger" onClick={this.deleteVideo} size="small">Delete video</Button>
                </div>
              </div>
            ) : (null)
        ) : (null)}
        <p/>
        <header className="jumbotron py-3 text-center">
          <h5>{this.state.video.points}&nbsp;<AiFillLike/></h5>
          <h2>
            <strong>{this.state.video.videoTitle}</strong>
          </h2>
          <hr/>
          <h8>{this.state.userLink}</h8>
        </header>
        <div className="container py-6 player-wrapper">
          <ReactPlayer
            className="react-player"
            url={this.state.video.videoUrl}
            controls
            width="100%"
            height="100%"
          />
        </div>
        <hr/>
        {this.state.video.videoDescription}
        <hr/>
        {(this.state.userReady && (this.state.currentUser.id !== this.state.video.userId)) ? (
          (this.state.alreadyVoted) ? (
            <div className="alert text-center alert-info">
              You have already rated this Video!
            </div>
          ) : (
            <div className="container py-3 text-center">
              <VideoRate dataFromParent = {this.state}/>
              <hr/>
            </div>
          )
        ) : (
          <div className="alert text-center alert-info">
            You can not rate your own Video!
          </div>
        )}
        <p/>
      </div>
    );
  }

}
