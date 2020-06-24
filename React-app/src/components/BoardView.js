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
const mystyle = (threadColor) => {
  return {
    stroke: `rgb(${threadColor.r},${threadColor.g},${threadColor.b})`,
    strokeWidth: 2,
    backgroundColor: "transparent"
  }
}
const svgstyle = {
  backgroundColor: "transparent",
  position: "absolute",
  top: 100,
  left: 100
}
class BoardView extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      edges: [],
      circles: null,
      sc: null
    }
    let x = this.state.edges
    let y = this
    this.props.stompClient.subscribe('/topic/edge', (message) => {
      const data = JSON.parse(message.body)
      // let x = this.state.edges
      x.push(data)
      console.log(data)
      y.setState({ edges: x })
    })
  }
  static getDerivedStateFromProps(props, state) {
    return {
      sc: props.stompClient
    };
  }
  componentDidMount() {

  }


  // function addlines(data){
  //   egs.push(data)
  //   setEdges(egs)
  //   lines = eg.map(edge=>{
  //     return <line x1={edge.firstPoint.x} y1={edge.firstPoint.y} x2={edge.secondPoint.x} y2={edge.secondPoint.y}  style={mystyle} ></line>
  //   })
  // }


  // var lines = edges.map(edge=>{
  //   return <line x1={edge.firstPoint.x} y1={edge.firstPoint.y} x2={edge.secondPoint.x} y2={edge.secondPoint.y}  style={mystyle} ></line>
  // })
  // console.log(lines)
  render() {
    return (
      <div>
        <svg height="1000px" width="1000px" style={svgstyle}>
          {
            this.props.points.map(point => {
              return <circle cx={point.x} cy={point.y} fill="black" r="5"></circle>
            })
          }
        </svg>
        <svg height="1000px" width="1000px" style={svgstyle}>
          {
            this.state.edges.map(edge => {
              console.log(edge)
              return <line x1={edge.firstPoint.x} y1={edge.firstPoint.y} x2={edge.secondPoint.x} y2={edge.secondPoint.y} style={mystyle(edge.firstPoint.threadColor)} ></line>
            })
          }
        </svg>
      </div>
    );
  }
}

export default BoardView
