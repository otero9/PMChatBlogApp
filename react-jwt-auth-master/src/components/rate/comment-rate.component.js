import React, { Component } from "react";
import RateService from "../../services/rate.service";
import { AiFillLike, AiFillDislike } from "react-icons/ai";
import IconButton from "@material-ui/core/IconButton";


export default class CommentRate extends Component {

  constructor(props) {
    super(props);
    this.newPositiveRate = this.newPositiveRate.bind(this);
    this.newNegativeRate = this.newNegativeRate.bind(this);

  }

  newPositiveRate() {
    RateService.rateComment(this.props.dataFromParent.commentId,this.props.dataFromParent.currentUserId,1);
    window.location.reload();
  }

  newNegativeRate() {
    RateService.rateComment(this.props.dataFromParent.commentId,this.props.dataFromParent.currentUserId,-1);
    window.location.reload();
  }

  render() {
    return (
      <React.Fragment>
        <div className="text-muted text-center">
            <span className="text-muted text-center">What do you think about this comment?</span>
            <IconButton color="primary" onClick={this.newPositiveRate}><AiFillLike/></IconButton>&nbsp;
            <IconButton color="secondary" onClick={this.newNegativeRate}><AiFillDislike/></IconButton>
        </div>
      </React.Fragment>
    );
  }
}
