import React, { Component } from "react";
import TemplateService from "../../services/template.service";
import { MDBDataTableV5 } from 'mdbreact';
import { MDBTable } from 'mdbreact';
import { Link } from "react-router-dom";
import "@fortawesome/fontawesome-free/css/all.min.css";
import "bootstrap-css-only/css/bootstrap.min.css";
import { CgTemplate } from "react-icons/cg";

export default class Templates extends Component {

  constructor(props) {
    super(props);
    this.renderViewTemplate = this.renderViewTemplate.bind(this);

    this.state = {
        templates: {}
    };
  }

  renderViewTemplate(templateId,templateTitle) {
    const urlViewTemplate = "/template/"+templateId;
    return <Link to={urlViewTemplate}>{templateTitle}</Link>;
  }

  componentDidMount() {
    TemplateService.getAllTemplates().then(
      response => {
        this.setState({
            templates: {
              columns : [
                { label: 'Title', field: 'title'},
                { label: 'Points', field: 'points'},
                { label: 'Category', field: 'category'},
                { label: 'User', field: 'user'},
                { label: 'Published', field: 'createdDate'}
              ],
              rows: (response.data!==undefined && response.data.length > 0 ? (response.data.map((template, index) => (
              { title: this.renderViewTemplate(template.templateId,template.templateTitle),
                points: template.points, category: template.categoryName,
                user: template.userName, createdDate: template.createdDate.substring(0, 10)
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

    return (
        <div className="container">
            <p/>
            <header className="jumbotron py-4 text-center">
                <h3><CgTemplate/>&nbsp;&nbsp;All Templates</h3>
            </header>
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
        </div>
    );
  }
}
