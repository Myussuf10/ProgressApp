import React, {useState} from 'react';
import {createStackNavigator} from '@react-navigation/stack';
import {NavigationContainer} from '@react-navigation/native';
import {
  KeyboardAvoidingView,
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  TextInput,
  TouchableOpacity,
  useColorScheme,
  View,
} from 'react-native';
import Login from './components/Login';
import ForgotPassword from './components/ForgotPassword';

const Stack = createStackNavigator();

const App = () => {
  const [email, setEmail] = useState(' ');
  const [password, setPassword] = useState(' ');

  const globalScreenOptions = {
    headerStyle: {backgroundColor: '#608d56'},
    HeaderTitleStyle: {color: 'white'},
    headerTintColor: 'white',
  };

  return (
    <NavigationContainer>
      <Stack.Navigator screenOptions={globalScreenOptions}>
        <Stack.Screen name="Login" component={Login} />
        <Stack.Screen name="ForgotPassword" component={ForgotPassword} />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

const styles = StyleSheet.create({});

export default App;
