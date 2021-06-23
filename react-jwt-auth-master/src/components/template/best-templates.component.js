import React, { Component } from "react";
import TemplateService from "../../services/template.service";
import { MDBDataTableV5 } from 'mdbreact';
import { MDBTable } from 'mdbreact';
import { Link } from "react-router-dom";
import "@fortawesome/fontawesome-free/css/all.min.css";
import "bootstrap-css-only/css/bootstrap.min.css";
import { GiPodium } from "react-icons/gi";
import { CgTemplate } from "react-icons/cg";


export default class BestTemplates extends Component {

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
      TemplateService.getBestTemplates().then(
        response => {
          this.setState({
              templates: {
                columns : [
                  { label: 'Points', field: 'points'},
                  { label: 'Title', field: 'title'},
                  { label: 'Category', field: 'category'},
                  { label: 'User', field: 'user'},
                  { label: 'Published', field: 'createdDate'}
                ],
                rows: (response.data!==undefined && response.data.length > 0 ? (response.data.map((template, index) => (
                { points: template.points, title: this.renderViewTemplate(template.templateId, template.templateTitle),
                  category: template.categoryName, user: template.userName,
                  createdDate: template.createdDate.substring(0, 10)
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
                  <h1><GiPodium/></h1>
                  <h3><CgTemplate/>&nbsp;&nbsp;Best Templates</h3>
              </header>
              <MDBTable responsive>
                  <MDBDataTableV5
                    hover
                    striped
                    paging={false}
                    searching={false}
                    data={this.state.templates}/>
              </MDBTable>
          </div>
      );
    }

}
