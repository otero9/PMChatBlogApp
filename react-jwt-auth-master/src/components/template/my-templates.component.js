import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import TemplateService from "../../services/template.service";
import UserService from "../../services/user.service";
import { MDBDataTableV5 } from 'mdbreact';
import { MDBTable } from 'mdbreact';
import { Link } from "react-router-dom";
import "@fortawesome/fontawesome-free/css/all.min.css";
import "bootstrap-css-only/css/bootstrap.min.css";
import { CgTemplate } from "react-icons/cg";

export default class MyTemplates extends Component {

  constructor(props) {
    super(props);
    this.renderViewTemplate = this.renderViewTemplate.bind(this);

    this.state = {
        redirect: null,
        userReady: false,
        currentUser: { username: "" },
        templates: {}
    };
  }

  renderViewTemplate(templateId,templateTitle) {
    const urlViewTemplate = "/template/"+templateId;
    return <Link to={urlViewTemplate}>{templateTitle}</Link>;
  }

  componentWillMount() {
    const currentUser = UserService.getCurrentUser();
    if (!currentUser) this.setState({ redirect: "/login" });
    this.setState({ currentUser: currentUser, userReady: true });
  }

  componentDidMount() {
    TemplateService.getTemplatesByUserId(this.state.currentUser.id).then(
      response => {
        this.setState({
            templates: {
              columns : [
                { label: 'Title', field: 'title'},
                { label: 'Published', field: 'createdDate'},
                { label: 'Category', field: 'category'},
                { label: 'Points', field: 'points'}
              ],
              rows: (response.data!==undefined && response.data.length > 0 ? (response.data.map((template, index) => (
              { title: this.renderViewTemplate(template.templateId,template.templateTitle),
                createdDate: template.createdDate.substring(0, 10),
                category: template.categoryName, points: template.points
              }))) : [])
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

    if (this.state.redirect) {
      return <Redirect to={this.state.redirect} />
    }

    const { currentUser } = this.state;

    return (
        <div className="container">
            <p/>
            <header className="jumbotron py-4 text-center">
                <h3><CgTemplate/>&nbsp;&nbsp;My Templates</h3>
            </header>
            {(currentUser) ? (
                <MDBTable responsive>
                    <MDBDataTableV5
                      hover
                      striped
                      order={['createdDate', 'desc']}
                      data={this.state.templates}
                      entriesOptions={[20, 40, 60]}
                      entries={20}
                      searchTop
                      searchBottom={false}/>
                </MDBTable>
            ): (<Redirect to="/login"/>)}
        </div>
    );
  }

}
