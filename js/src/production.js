//////////////////////////////////

function userRepository(){
  var self = {};

  self.retrieveUsers = function(){
      // imagine this retrieves a list of objects from the localStorage
  };

  self.store = function(user){
      // imagine this stores the object in the localStorage
  };

  return self;
}

//////////////////////////////////

function User(fields){
    if (fields)
       this._email = fields.email;
}
User.prototype.hasEmail = function(email){
    return this._email == email;
};
User.prototype.asRaw = function(){
    return {email: this._email};
};



//////////////////////////////////

function userService(userRepo){
   var self = {};
   var repo = userRepo;

   self.findBy = function(email){
      var rawUsers = repo.retrieveUsers();
      for(var i = 0; i < rawUsers.length; i++)
        if (rawUsers[i].email == email)
            return new User({email: email});
   };
   self.add = function(user){
        repo.store(user.asRaw());
   };
   self.sayHello = function(){
        alert('hello!');
   };

   return self;
}



