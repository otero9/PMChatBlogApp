import React, { Component } from "react";
import RateService from "../../services/rate.service";
import { AiFillLike, AiFillDislike } from "react-icons/ai";
import IconButton from "@material-ui/core/IconButton";


export default class TemplateRate extends Component {

  constructor(props) {
    super(props);
    this.newPositiveRate = this.newPositiveRate.bind(this);
    this.newNegativeRate = this.newNegativeRate.bind(this);

  }

  newPositiveRate() {
    RateService.rateTemplate(this.props.dataFromParent.templateId,this.props.dataFromParent.currentUser.id,1);
    window.location.reload();
  }

  newNegativeRate() {
    RateService.rateTemplate(this.props.dataFromParent.templateId,this.props.dataFromParent.currentUser.id,-1);
    window.location.reload();
  }

  render() {
    return (
      <React.Fragment>
        <div className="text-muted mb-4 text-center">
            <span className="text-muted text-center">What do you think about this template?</span>
            <p/>
            <IconButton color="primary" onClick={this.newPositiveRate}><AiFillLike/></IconButton>&nbsp;
            <IconButton color="secondary" onClick={this.newNegativeRate}><AiFillDislike/></IconButton>
        </div>
      </React.Fragment>
    );
  }
}
