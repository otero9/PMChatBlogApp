import React, { Component } from "react";
import UserService from "../../services/user.service";
import { MDBDataTableV5 } from 'mdbreact';
import { MDBTable } from 'mdbreact';
import { Link } from "react-router-dom";
import "@fortawesome/fontawesome-free/css/all.min.css";
import "bootstrap-css-only/css/bootstrap.min.css";
import { GiPodium } from "react-icons/gi";
import { BiUser } from "react-icons/bi";


export default class BestUsers extends Component {

    constructor(props) {
      super(props);
      this.renderViewUser = this.renderViewUser.bind(this);

      this.state = {
          users: {}
      };
    }

    renderViewUser(userId, userName) {
      const urlViewUser = "/user/"+userId;
      return <Link to={urlViewUser}>{userName}</Link>;
    }

    componentDidMount() {
      UserService.getBestUsers().then(
        response => {
          this.setState({
              users: {
                columns : [
                  { label: 'Points', field: 'points'},
                  { label: 'UserName', field: 'username'},
                  { label: 'Blogs', field: 'blogs'},
                  { label: 'Comments', field: 'comments'},
                  { label: 'Templates', field: 'templates'},
                  { label: 'Videos', field: 'videos'}
                ],
                rows: response.data.map((user, index) => (
                { points: user.points, username: this.renderViewUser(user.userId, user.userName),
                  blogs: user.countBlogs, comments: user.countComments, templates: user.countTemplates,
                  videos: user.countVideos
                }))
              }
          });
        },
        error => {
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
          <div className="container">
              <p/>
              <header className="jumbotron py-4 text-center">
                  <h1><GiPodium/></h1>
                  <h3><BiUser/>&nbsp;&nbsp;Best Users</h3>
              </header>
              <MDBTable responsive>
                  <MDBDataTableV5
                    hover
                    striped
                    data={this.state.users}
                    entriesOptions={[20, 40, 60]}
                    entries={20}
                    pagingTop
                    searchTop
                    searchBottom={false}/>
              </MDBTable>
          </div>
      );
    }

}
