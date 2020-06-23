import React from 'react'
import makeStyles from "@material-ui/styles/makeStyles";
import Card from '@material-ui/core/Card';
import CardMedia from '@material-ui/core/CardMedia';
import CardContent from '@material-ui/core/CardContent';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Link from '@material-ui/core/Link';
import Sparta from "../resources/sparta.jpg";


const useStyles = makeStyles((theme) => ({
  card: {
    position: "fixed",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    padding: theme.spacing(2),
    minWidth: "300px",
    width: "24%"
  },
  media: {
    height: 0,
    paddingTop: '60%',
    backgroundSize: '60%',
  },
  form: {
    display: "flex",
    flexDirection: "column"
  },
  loginButton: {
    padding: `${theme.spacing(1)}px ${theme.spacing(3)}px`,
    margin: `${theme.spacing(2)}px auto`
  }
}));
const mystyle = {
  stroke:'rgb(255,0,0)',
  strokeWidth:2,
  backgroundColor:"transparent"
}
const svgstyle = {
  backgroundColor:"transparent",
  position:"absolute",
  top: 50,
  left: 50
}
class BoardView extends React.Component{
  constructor(props){
    super(props)
    this.state={
      points:[[]],
      edges:null,
      circles: null
    }
  }

  componentDidMount(){
    var arr = this.state.points
    // var i
    // for(i=0;i<100;i++){
    //   arr.push([Math.floor(Math.random() * 1000),Math.floor(Math.random() * 1000)])
    // }
    // this.setState({points : arr})
    // var circle = this.state.points.map( point => {
    //   return <circle cx={point[0]} cy={point[1]} r="5" fill="red"/>
    // })
    // this.setState({circles: circle}) 
    // var j
    var edge = []
    // for(j=0;j<arr.length-1;j++){
    //   edge.push(<line x1={this.state.points[j][0]} y1={this.state.points[j][1]} x2={this.state.points[j+1][0]} y2={this.state.points[j+1][1]} style={mystyle}></line>)
    // }
    // this.setState({edges: edge})
    setInterval(()=>{
      arr.push([Math.floor(Math.random() * 1000),Math.floor(Math.random() * 1000)])
      this.setState({points : arr})
      var circle = this.state.points.map( point => {
        return <circle cx={point[0]} cy={point[1]} r="5" fill="red"/>
      })
      var j
      for(j=0;j<arr.length-1;j++){
        edge.push(<line x1={this.state.points[j][0]} y1={this.state.points[j][1]} x2={this.state.points[j+1][0]} y2={this.state.points[j+1][1]} style={mystyle}></line>)
      }
      this.setState({edges: edge})
      this.setState({circles: circle}) 
    },500)
  }

  render() {
    return (
      <div>
        <svg height="1000px" width="1000px" style={svgstyle}>
          {
            this.state.circles
          }
        </svg>
        <svg height="1000px" width="1000px" style={svgstyle}>
          {
            this.state.edges
          }
        </svg>
      </div>
    );
  }
}

export default BoardView
