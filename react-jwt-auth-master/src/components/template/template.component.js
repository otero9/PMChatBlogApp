import React, { Component } from "react";
import TemplateService from "../../services/template.service";
import { AiFillLike } from "react-icons/ai";
import TemplateRate from "../rate/template-rate.component";
import ErrorPage from "../error/error-page.component";
import RateService from "../../services/rate.service";
import UserService from "../../services/user.service";
import { Link } from "react-router-dom";
import { Button } from "react-bootstrap";
import { confirmAlert } from "react-confirm-alert";
import "react-confirm-alert/src/react-confirm-alert.css";


export default class Template extends Component {

  constructor(props) {
    super(props);
    this.renderViewUser = this.renderViewUser.bind(this);

    this.state = {
        currentUser: undefined,
        userReady: false,
        templateId: this.props.match.params.id,
        template: "",
        alreadyVoted: false,
        userLink: undefined
    };
  }

  downloadFile = () =>{
    TemplateService.downloadFile(this.state.template.fileId).then(
      response => {
        if (response.status === 200) {
          console.log(this.state.template.fileName);
          const url = window.URL.createObjectURL(new Blob([response.data]));
          const link = document.createElement('a');
          link.href = url;
          link.setAttribute('download', this.state.template.fileName);
          document.body.appendChild(link);
          link.click();
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

  updateTemplate = () =>{
    this.props.history.push("/updateTemplate/"+this.state.templateId);
    window.location.reload();
  }

  renderViewUser(userId,userName) {
    const urlViewUser = "/user/"+userId;
    return <Link to={urlViewUser}>{userName}</Link>;
  }

  deleteTemplate = () =>{
    confirmAlert({
      title: 'Confirm Template deletion',
      message: 'Are you sure to delete your Template and their ratings?',
      buttons: [
        {
          label: 'Yes',
          onClick: () => TemplateService.deleteTemplate(this.state.templateId).then(
            (response) => {
              this.props.history.push("/myTemplates");
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
    TemplateService.getTemplateById(this.state.templateId).then(
      response => {
        if (response.status === 200) {
          this.setState({
            template: response.data,
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

      RateService.getUserTemplateRate(this.state.templateId, user.id).then(
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
            (this.state.currentUser.id === this.state.template.userId) ? (
              <div>
                <p/>
                <div className="jumbotron py-3 text-center">
                    <Button variant="primary" onClick={this.updateTemplate} size="small">Update template</Button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <Button variant="danger" onClick={this.deleteTemplate} size="small">Delete template</Button>
                </div>
              </div>
            ) : (null)
        ) : (null)}
        <p/>
        <header className="jumbotron py-3 text-center">
          <h5>{this.state.template.points}&nbsp;<AiFillLike/></h5>
          <h2>
            <strong>{this.state.template.templateTitle}</strong>
          </h2>
          <hr/>
          <h8>{this.state.userLink}</h8>
        </header>
        {this.state.template.templateDescription}
        <hr/>
        <div className="py-3 text-center">
          <Button variant="link" onClick={this.downloadFile} size="small">Download File</Button>
          <br/>
        </div>
        <hr/>
        {(this.state.userReady) ? (
          <div className="container py-3 text-center">
              {(this.state.userReady && (this.state.currentUser.id !== this.state.template.userId)) ? (
                (this.state.alreadyVoted) ? (
                  <div className="alert text-center alert-info">
                    You have already rated this Template!
                  </div>
                ) : (
                  <div className="container py-3 text-center">
                    <TemplateRate dataFromParent = {this.state}/>
                  </div>
                )
              ) : (
                <div className="alert text-center alert-info">
                  You can not rate your own Template!
                </div>
              )}
          </div>
        ) : (null)}
      </div>
    );
  }

}
