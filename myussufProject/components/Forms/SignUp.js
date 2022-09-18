import { StyleSheet, Text, View, TouchableOpacity, KeyboardAvoidingView} from 'react-native'
import React from 'react'
import Input from './Input'
import Parent from '../Parent'
import { useState } from 'react'
import { storeStudent, fetchSubjects, fetchParents,} from '../util/http'
import { useEffect } from 'react'
import { useContext } from 'react'
import { AuthContext } from '../store/AuthContext'


const SignUp = ({navigation}) => {
  const [student, setStudent] = useState({
    firstname: '',
    lastname: '',
    school: '',
    dob:'',
  });
  const[Parents, setParent] = useState();
  const [Maths, setMaths] = useState(false); 
  const [English, setEnglish] = useState(false); 
  const [Science, setScience] = useState(false); 
  const [AvailSubjects, setSubjects] = useState({})
  const [studentSubject, setStudentSubjects] = useState([])
  const authCtx = useContext(AuthContext);

  
  useEffect(()=>{
       
    console.log(authCtx.token)
  }, [Parents])
 

  const inputHandler=(inputId, enteredValue)=>{
    setStudent((currentInput)=>{
      return{
        ...currentInput,
        [inputId]:enteredValue,
      };
    });
  }


   async function Assign() {
      //student.dob= new Date(student.dob);
      // var pattern = /(\d{2})\-(\d{2})\-(\d{4})/;
      // const date = new Date(student.dob)
      // console.log(date.getTime())
      const sudid = await storeStudent(Parents, student, authCtx.token);
      console.log(sudid)
      navigation.pop()
          
  }
 
  

  return (
    <KeyboardAvoidingView behavior='padding' style={styles.screen}>
      <Text style={styles.header}>Signing Up</Text>
      <Input label="First Name" textInputConfig={{
        placeholder: 'First Name',
        onChangeText: inputHandler.bind(this,'firstname'),
        value: student.firstname }} />
      <Input label="Last Name" textInputConfig={{
        placeholder: 'Last Name', 
        onChangeText: inputHandler.bind(this,'lastname'),
        value: student.lastname }} />

      <Input label="DOB  " textInputConfig={{
        keyboardType: 'number-pad',
        placeholder: 'DD-MM-YYYY',
        onChangeText: inputHandler.bind(this,'dob'),
        value: student.dob }} />
      <Input label="School  " textInputConfig={{
        placeholder: 'School',
        onChangeText: inputHandler.bind(this,'school'),
        value: student.school }} />
      <View style={styles.dropdown}>
        <Parent setParent={setParent} />
        <TouchableOpacity
        title="AddParent"
        onPress={() => {navigation.navigate('AddParent')

        }}
        style={styles.parentbutton}>
        <Text style={{
          textAlign: 'center',
          fontSize: 20,
          color: "black",
        }}> Add Parent </Text>
      </TouchableOpacity>
        
       
      </View>

      <TouchableOpacity
        title="Submit"
        onPress={() => {Assign()

        }}
        style={styles.button}>
        <Text style={{
          textAlign: 'center',
          fontSize: 20,
          color: "white",
        }}> Submit </Text>
      </TouchableOpacity>
    </KeyboardAvoidingView>
  )
}


export default SignUp;


const styles = StyleSheet.create({
  header: {
    fontSize: 22,
    textAlign: 'center',
    margin: 8
  },
  parentbutton:{
    backgroundColor: '#EFEFEF',
    padding: 12,
    width: 190
  },
  button: {
    backgroundColor: '#608d56',
    borderRadius: 5,
    padding: 10,
    marginTop: 10,
    },
    checkboxWrapper: {
      alignItems: 'center',
      alignContent: 'center',
      flexDirection: 'row',
      paddingVertical: 5,
      flexWrap: 'wrap',
      padding: 15,
    },
  dropdown: {
    display: 'flex',
    flexDirection: 'row',
    alignItems: 'center'
  },
  screen: { flex: 1, backgroundColor: '#B4B4B4' },
    text:{
    margin: 8,
  }

})