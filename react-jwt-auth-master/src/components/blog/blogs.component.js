import React, { Component } from "react";
import BlogService from "../../services/blog.service";
import { MDBDataTableV5 } from 'mdbreact';
import { MDBTable } from 'mdbreact';
import { Link } from "react-router-dom";
import { BiBookAlt } from "react-icons/bi";


export default class Blogs extends Component {

  constructor(props) {
    super(props);
    this.renderViewBlog = this.renderViewBlog.bind(this);

    this.state = {
        blogs: {}
    };
  }

  renderViewBlog(blogId,title) {
    const urlViewBlog = "/blog/"+blogId;
    return <Link to={urlViewBlog}>{title}</Link>;
  }

  componentDidMount() {
    BlogService.getAllBlogs().then(
      response => {
        this.setState({
            blogs: {
              columns : [
                { label: 'Title', field: 'title'},
                { label: 'Points', field: 'points'},
                { label: 'Category', field: 'category'},
                { label: 'User', field: 'user'},
                { label: 'Published', field: 'createdDate'}
              ],
              rows: (response.data!==undefined && response.data.length > 0 ? (response.data.map((blog, index) => (
              { title: this.renderViewBlog(blog.blogId,blog.title),
                points: blog.points, category: blog.categoryName,
                user: blog.userName, createdDate: blog.createdDate.substring(0, 10)
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
                <h3><BiBookAlt/>&nbsp;All Blogs</h3>
            </header>
            <MDBTable responsive>
                <MDBDataTableV5
                  hover
                  striped
                  order={['createdDate', 'desc']}
                  entriesOptions={[20, 40, 60]}
                  entries={20}
                  data={this.state.blogs}
                  searchTop
                  searchBottom={false}
                  />
            </MDBTable>
        </div>
    );
  }
}
