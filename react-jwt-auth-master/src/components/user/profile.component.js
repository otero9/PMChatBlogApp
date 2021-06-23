import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import UserService from "../../services/user.service";
import { CgProfile } from "react-icons/cg";
import { FiMail, FiCalendar } from "react-icons/fi";
import { FaRegComment } from "react-icons/fa";
import { BsPersonFill } from "react-icons/bs";
import { BiTime } from "react-icons/bi";
import { AiOutlineLike } from "react-icons/ai";
import moment from "moment";
import { Button } from "react-bootstrap";
import { confirmAlert } from "react-confirm-alert";
import "react-confirm-alert/src/react-confirm-alert.css";
import ErrorPage from "../error/error-page.component";
import { BiBookAlt, BiVideo } from "react-icons/bi";
import { CgTemplate } from "react-icons/cg";


export default class Profile extends Component {

  constructor(props) {
    super(props);

    this.state = {
      redirect: null,
      userReady: false,
      countComments: 0,
      countBlogs: 0,
      countVideos: 0,
      countTeplates:0,
      currentUser: { username: "" }
    };
  }

  changePassword = () =>{
    this.props.history.push("/changePassword");
    window.location.reload();
  }

  updateProfile = () =>{
    this.props.history.push("/updateProfile");
    window.location.reload();
  }

  deleteAccount = () =>{
    confirmAlert({
      title: 'Confirm account deletion',
      message: 'Are you sure to delete your Account, blogs, comments and valorations?',
      buttons: [
        {
          label: 'Yes',
          onClick: () => UserService.deleteAccount(this.state.currentUser.id).then(
            (response) => {
              UserService.logout();
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

  componentWillMount() {
    const currentUser = UserService.getCurrentUser();
    if (!currentUser) this.setState({ redirect: "/login" });
    this.setState({ currentUser: currentUser, userReady: true });
    if (currentUser) {
        UserService.getUserById(currentUser.id).then(
        response => {
          if (response.status === 200) {
            this.setState({
              countComments: response.data.countComments,
              countBlogs: response.data.countBlogs,
              countTemplates: response.data.countTemplates,
              countVideos: response.data.countVideos,
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
        });
    }
  }

  render() {
    if (this.state.redirect) {
      return <Redirect to={this.state.redirect} />
    }

    const { currentUser } = this.state;

    return (
      <div className="card card-container">
          {(currentUser) ? (
              <div className="container py-3">
                  <header className="jumbotron py-4 text-center">
                      <CgProfile size={50} color="dark grey"/>
                      <br/>
                      <br/>
                      <h4>{currentUser.username}</h4>
                      <br/>
                      <h5>{currentUser.points}&nbsp;<AiOutlineLike/><br/>
                      &nbsp;&nbsp;{this.state.countBlogs}&nbsp;<BiBookAlt/>
                      &nbsp;&nbsp;{this.state.countComments}&nbsp;<FaRegComment/>
                      &nbsp;&nbsp;{this.state.countTemplates}&nbsp;<CgTemplate/>
                      &nbsp;&nbsp;{this.state.countVideos}&nbsp;<BiVideo/>
                      </h5>
                  </header>
                  <strong><BsPersonFill/>&nbsp;First name:</strong>&nbsp;
                  {currentUser.firstname}
                  <hr/>
                  <strong><BsPersonFill/>&nbsp;Last name:</strong>&nbsp;
                  {currentUser.lastname}
                  <hr/>
                  <strong><FiMail/>&nbsp;Email:</strong>&nbsp;
                  {currentUser.email}
                  <hr/>
                  <strong><FiCalendar/>&nbsp;Birth date:</strong>&nbsp;
                  {moment(currentUser.birthDate).format("DD-MM-YYYY")}
                  <hr/>
                  <strong><BiTime/>&nbsp;Register date:</strong>&nbsp;
                  {moment(currentUser.createdDate).format("DD-MM-YYYY")}
                  <hr/>
                  <div className="text-center">
                      <Button variant="primary" onClick={this.updateProfile} size="small">Update profile</Button>
                      <p/>
                      <Button variant="secondary" onClick={this.changePassword} size="small">Change password</Button>
                      <p/>
                      <Button variant="danger" onClick={this.deleteAccount} size="small">Delete account</Button>
                  </div>
              </div>
          ): (<Redirect to="/login"/>)}
      </div>
    );
  }

}
