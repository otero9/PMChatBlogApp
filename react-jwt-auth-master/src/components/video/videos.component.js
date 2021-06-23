import React, { Component } from "react";
import VideoService from "../../services/video.service";
import { MDBDataTableV5 } from 'mdbreact';
import { MDBTable } from 'mdbreact';
import { Link } from "react-router-dom";
import { BiVideo } from "react-icons/bi";


export default class Videos extends Component {

  constructor(props) {
    super(props);
    this.renderViewVideo = this.renderViewVideo.bind(this);

    this.state = {
        videos: {}
    };
  }

  renderViewVideo(videoId,videoTitle) {
    const urlViewVideo = "/video/"+videoId;
    return <Link to={urlViewVideo}>{videoTitle}</Link>;
  }

  componentDidMount() {
    VideoService.getAllVideos().then(
      response => {
        this.setState({
            videos: {
              columns : [
                { label: 'Title', field: 'title'},
                { label: 'Points', field: 'points'},
                { label: 'Category', field: 'category'},
                { label: 'User', field: 'user'},
                { label: 'Published', field: 'createdDate'}
              ],
              rows: (response.data!==undefined && response.data.length > 0 ? (response.data.map((video, index) => (
              { title: this.renderViewVideo(video.videoId,video.videoTitle),
                points: video.points, category: video.categoryName,
                user: video.userName, createdDate: video.createdDate.substring(0, 10)
              }))): [])
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
                <h3><BiVideo/>&nbsp;&nbsp;All Videos</h3>
            </header>
            <MDBTable responsive>
                <MDBDataTableV5
                  hover
                  striped
                  order={['createdDate', 'desc']}
                  data={this.state.videos}
                  entriesOptions={[20, 40, 60]}
                  entries={20}
                  searchTop
                  searchBottom={false}/>
            </MDBTable>
        </div>
    );
  }
}
