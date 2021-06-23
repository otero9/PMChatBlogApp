import React, { Component } from "react";
import UserService from "../../services/user.service";
import { CgProfile } from "react-icons/cg";
import { FiMail } from "react-icons/fi";
import { FaRegComment } from "react-icons/fa";
import { BiTime } from "react-icons/bi";
import { AiOutlineLike } from "react-icons/ai";
import moment from "moment";
import ErrorPage from "../error/error-page.component";
import { BiBookAlt, BiVideo } from "react-icons/bi";
import { CgTemplate } from "react-icons/cg";


export default class User extends Component {

  constructor(props) {
    super(props);

    this.state = {
        userId: this.props.match.params.id,
        user: ""
    };
  }

  componentWillMount(){
    UserService.getUserById(this.state.userId).then(
      response => {
        if (response.status === 200) {
          this.setState({
            user: response.data
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
  }

  render() {

    return (
      <div className="card card-container">
          <div className="container py-3">
              <header className="jumbotron py-4 text-center">
                  <CgProfile size={50} color="dark grey"/>
                  <br/>
                  <br/>
                  <h4>{this.state.user.userName}</h4>
                  <br/>
                  <h5>{this.state.user.points}&nbsp;<AiOutlineLike/><br/>
                  &nbsp;&nbsp;{this.state.user.countBlogs}&nbsp;<BiBookAlt/>
                  &nbsp;&nbsp;{this.state.user.countComments}&nbsp;<FaRegComment/>
                  &nbsp;&nbsp;{this.state.user.countTemplates}&nbsp;<CgTemplate/>
                  &nbsp;&nbsp;{this.state.user.countVideos}&nbsp;<BiVideo/>
                  </h5>
              </header>
              <strong><FiMail/>&nbsp;Email:</strong>&nbsp;
              {this.state.user.email}
              <hr/>
              <strong><BiTime/>&nbsp;Register date:</strong>&nbsp;
              {moment(this.state.user.createdDate).format("DD-MM-YYYY")}
              <hr/>
          </div>
      </div>
    );
  }

}
